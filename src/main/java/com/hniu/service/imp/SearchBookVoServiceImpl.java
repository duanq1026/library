package com.hniu.service.imp;

import com.hniu.entity.vo.BookVo;
import com.hniu.mapper.BooksMapper;
import com.hniu.service.SearchBookVoService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SearchBookVoServiceImpl implements SearchBookVoService {

	@Autowired
	private BooksMapper booksMapper;
	@Autowired
	private SolrClient solrClient;

	
	
	@Override
	public String importAllItems() {
		try {
			solrClient.deleteByQuery("*:*",1000);
			List<BookVo> itemList = booksMapper.getBookList();
			for(BookVo bookVo : itemList) {
				SolrInputDocument document = new SolrInputDocument();

				document.addField("id", bookVo.getBookId());
				document.addField("book_book_name", bookVo.getBookName());
				document.addField("book_type_name", bookVo.getBookTypeName());
				document.addField("book_author", bookVo.getAuthor());
				document.addField("book_press", bookVo.getPress());
				document.addField("book_img_path", bookVo.getImgPath());
				solrClient.add(document);
			}
			
			//提交
			solrClient.commit();
			//返回导入成功
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return "数据导入时发生异常";
		} 
	}

}
