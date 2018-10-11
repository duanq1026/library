package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.entity.Readers;
import com.hniu.entity.vo.PageVo;
import com.hniu.entity.vo.ReaderVo;
import com.hniu.service.ReaderService;
import com.hniu.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: ReaderController
 * @Description: 读者控制层
 * @author yangf
 * @date 2018年5月2日 上午10:15:24
 * @version 1.0
 */
@RestController
public class ReaderController extends Base {
	
	@Autowired
    ReaderService readerService;
	@Autowired
	RedisUtil redisUtil;
	/**
     * 
     * Title: selectAll
     * Description: 分页模糊查询读者列表
     * @param page
     * @return
``	*/
	@GetMapping(value="/readers")
	public Object selectAll(PageVo<ReaderVo> page) {
		readerService.selectAll(page);
		if(page.getDateList()!=null) {
			return packaging(StateCode.SUCCESS,page);
		}
		return packaging(StateCode.FAIL,null);
	}
	/**
	 * Title: deleteByPrimaryKey
	 * Description: 根据读者id主键删除读者
	 * @param rId
	 * @return
	 */
	@DeleteMapping(value="/readers/{rId}")
	public Object deleteByPrimaryKey(@PathVariable Integer rId) {
		if(readerService.deleteByPrimaryKey(rId)>0) {
			return packaging(StateCode.SUCCESS,null);
		}
		return packaging(StateCode.FAIL,null);
	}
    /**
     * 
     * Title: insert
     * Description: 添加读者
     * @param reader
     * @return
     */
	@PostMapping(value="/readers")
	public Object insert(Readers reader) {
		if(readerService.insert(reader)!=null) {
			return packaging(StateCode.SUCCESS,reader);
		}
		return packaging(StateCode.FAIL,null);
	}
    /**
     * 
     * Title: selectByPrimaryKey
     * Description: 根据读者id主键查询读者
     * @param rId
     * @return
     */
	@GetMapping(value="/readers/{rId}")
	public Object selectByPrimaryKey(@PathVariable Integer rId) {
		
		ReaderVo reader = readerService.selectByPrimaryKey(rId);
		if(reader!=null) {
			return packaging(StateCode.SUCCESS,reader);
		}
		return packaging(StateCode.FAIL,null);
	}

	/**
	 * 微信查询读者
	 * @param token
	 * @return
	 */
	@GetMapping(value="/wx_readers/{token}")
	public Object wxSelectReader(@PathVariable String token) {
		String object = (String) redisUtil.getObject(token);
		if(object == null)
			return packaging(StateCode.FAIL,"error");
		String[] split = object.split(",");
		return selectByPrimaryKey(Integer.parseInt(split[2]));
	}
    /**
     * Title: updateByPrimaryKey
     * Description: 根据读者id主键修改信息
     * @param reader
     * @return
     */
	@PutMapping(value="/readers/{reaedrId}")
	public Object updateByPrimaryKey(Readers reader) {
		if(readerService.updateByPrimaryKey(reader)>0) {
			return packaging(StateCode.SUCCESS,reader);
		}
		return packaging(StateCode.FAIL,null);
	}


	/**
	 * 微信修改
	 * @param reader
	 * @param token
	 * @return
	 */
	@PutMapping(value="/wx_readers/{token}")
	public Object wxUpdateReader(Readers reader,@PathVariable String token) {
		String object = (String) redisUtil.getObject(token);
		if(object == null)
			return packaging(StateCode.FAIL,"error");
		String[] split = object.split(",");
		reader.setReaderId(Integer.parseInt(split[2]));
		return updateByPrimaryKey(reader);
	}


    /**
     * 
     * Title: selectByWechatId
     * Description: 根据微信ID查询读者信息
     * @param pageVo
     * @return
     */
	@GetMapping(value="/readers/search")
    public Object selectBySreach(PageVo<ReaderVo> pageVo) {
		readerService.selectByShearch(pageVo);
		if(pageVo.getDateList()!=null) {
			return packaging(StateCode.SUCCESS,pageVo);
		}
		return packaging(StateCode.FAIL,null);
	}
	
	 /**
     * 
     * Title: selectSexCount
     * Description: TODO 统计男/女数量
     * @return
     */
	@GetMapping(value="/readers/sex/count")
    public Object selectSexCount() {
		List<Map<String,Integer>> list = readerService.selectSexCount();
		if(list!=null) {
			return packaging(StateCode.SUCCESS,list);
		}
		return packaging(StateCode.FAIL,null);
	}
   /* *//**
     * 
     * Title: selectAgeCount
     * Description: 读者年龄间人数
     * @return
     *//*
	@GetMapping(value="/readers",params= {"ageMin","ageMax"})
	public Object selectAgeCount(@RequestParam("ageMin") Integer min, @RequestParam("ageMax") Integer max) {
		return packaging(StateCode.SUCCESS,readerService.selectAgeCount(min, max));
	}*/
	
	@GetMapping(value="/readers",params="readerCode")
    public Object selectByRCode(String readerCode) {
		ReaderVo reader = readerService.selectByRCode(readerCode);
		if(reader!=null) {
			return packaging(StateCode.SUCCESS,reader);
		}
		return packaging(StateCode.FAIL,null);
	}

	/**
	 * 逾期+1
	 * @param rId
	 * @return
	 */
	@PatchMapping(value="/readers/{rId}")
	public Object updateOverdue(@PathVariable Integer rId) {
		if(readerService.updateOverdue(rId)>0) {
			return packaging(StateCode.SUCCESS,null);
		}
		return packaging(StateCode.FAIL,null);
	}
	@GetMapping(value="/readers/other")
	public Object selectByOther(Readers reader) {
		List<ReaderVo> readers = readerService.selectByOther(reader);
		if(readers!=null) {
			return packaging(StateCode.SUCCESS,readers);
		}
		return packaging(StateCode.FAIL,null);
	}

	/**
	 * 判读用户是否能借书
	 * @param readerCode
	 * @return
	 */
	@GetMapping(value="/readers/borrows/{readerCode}")
	public Object selectBorrows(@PathVariable String readerCode){
		ReaderVo readerVo = readerService.selectBorrowBooks(readerCode);
		if(readerVo!=null) {
			return packaging(StateCode.SUCCESS,readerVo);
		}
		return packaging(StateCode.FAIL,null);
	}
}
