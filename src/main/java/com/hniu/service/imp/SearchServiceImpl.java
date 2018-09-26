package com.hniu.service.imp;

import com.hniu.entity.vo.BookVo;
import com.hniu.entity.vo.PageVo;
import com.hniu.mapper.SearchMapper;
import com.hniu.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper searchMapper;

    @Override
    public void shearch(PageVo<BookVo> pageBookVo) throws Exception {
        SolrQuery query = new SolrQuery();
        pageBookVo.getKeyword();
        query.setQuery(pageBookVo.getKeyword());

        query.setStart((pageBookVo.getPage()-1)*pageBookVo.getRows());
        query.setRows(pageBookVo.getRows());
        query.set("df", "book_keywords");
        query.setHighlight(true);
        query.addHighlightField("book_book_name");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        searchMapper.search(query,pageBookVo);
    }
}
