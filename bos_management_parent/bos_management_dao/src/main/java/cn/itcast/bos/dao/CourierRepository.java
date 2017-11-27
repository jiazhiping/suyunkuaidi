package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.Courier;

public interface CourierRepository 
	extends JpaRepository<Courier, Integer>, JpaSpecificationExecutor<Courier>{

	@Query("update Courier set deltag='1' where id=?1")
	@Modifying//当使用query执行更新或者删除时，因为要修改数据库内容，需要添加modifying注解
	void updateDeltag(Integer id);

	@Query("from Courier where deltag='0'")
	List<Courier> findNoDel();
	//还原
	@Query("update Courier set deltag='0' where id=?1")
	@Modifying
	void resove(int parseInt);

	@Query("from Courier where id = ?1")
    Courier findById(Integer courierId);

}
