package com.hniu.mapper;

import com.hniu.entity.Books;
import com.hniu.entity.vo.BookVo;
import com.hniu.entity.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class SearchMapper {

    @Autowired
    private SolrClient solrClient;

    /**
     * 根据查询条件查询所有库
     * @param query
     * @return
     * @throws Exception
     */
    public void search(SolrQuery query, PageVo<BookVo> pageBookVo)throws Exception{
        QueryResponse queryResponse = solrClient.query(query);

        SolrDocumentList solrDocumentList = ((QueryResponse) queryResponse).getResults();

        long total = solrDocumentList.getNumFound();

        pageBookVo.setTotal(total);
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        List<BookVo> bookVoList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList){
            BookVo bookVo = new BookVo();
            Integer id = Integer.parseInt((String) solrDocument.get("id"));
            bookVo.setBookId(id);
            bookVo.setPress((String)solrDocument.get("book_press"));
            bookVo.setImgPath((String)solrDocument.get("book_img_path"));
            bookVo.setBookTypeName((String) solrDocument.get("book_type_name"));
            bookVo.setAuthor((String) solrDocument.get("book_author"));
            Map<String, List<String>> map = highlighting.get(solrDocument.get("id"));
            bookVo.setBookName(toHighlighting(map.get("book_book_name"),solrDocument));

            //添加到列表
            bookVoList.add(bookVo);
        }
        pageBookVo.setDateList(bookVoList);
    }
    private String toHighlighting(List<String> list, SolrDocument solrDocument){
        String title = "";
        if(list != null && list.size()>0) {
            title = list.get(0);
        }else {
            title = (String)solrDocument.get("item_title");
        }
        return title;
    }
    /**
     * 添加一本到索引库
     */
    public String addBookVo(Books books){
        try {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", books.getBookId());
            document.addField("book_book_name", books.getBookName());
            document.addField("book_type_name", books.getBookTypeName());
            document.addField("book_author", books.getAuthor());
            document.addField("book_press", books.getPress());
            document.addField("book_img_path", books.getImgPath());
            solrClient.add(document);

            solrClient.commit();
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "数据导入时发生异常";
        }
    }
    /**
     * 修改一本到索引库
     */
    public String editBookVo(Books books){
        try {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", books.getBookId());
            if(StringUtils.isNoneBlank(books.getBookName())){
                document.addField("book_book_name", books.getBookName());
            }
            if(StringUtils.isNotBlank(books.getBookTypeName())) {
                document.addField("book_type_name", books.getBookTypeName());
            }
            if(StringUtils.isNotBlank(books.getAuthor())){
                document.addField("book_author", books.getAuthor());
            }
            if(StringUtils.isNotBlank(books.getPress())){
                document.addField("book_press", books.getPress());
            }
            if(StringUtils.isNotBlank(books.getImgPath())){
                document.addField("book_img_path", books.getImgPath());
            }
            solrClient.add(document);
            solrClient.commit();
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "数据导入时发生异常";
        }
    }
}
