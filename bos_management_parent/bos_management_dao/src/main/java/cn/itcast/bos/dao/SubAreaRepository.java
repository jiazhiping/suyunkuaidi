package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.SubArea;

public interface SubAreaRepository extends JpaRepository<SubArea, String>, JpaSpecificationExecutor<SubArea>{

	
	@Query("select a.province, count(*) from SubArea s join s.area a group by a.province")
	List<Object> findGroupedSubareas();


	
	List<SubArea> findByFixedAreaIdIsNull();

	@Query("select s from SubArea s join s.fixedArea f where f.id = ?1")
	List<SubArea> findGuanLianFixedArea(String id);

	@Query("update SubArea set fixedArea = null where fixedArea= ?1")
	@Modifying
	void updateFixedAreaIdIsNull(FixedArea fixedArea);

	@Query("update SubArea set fixedArea = ?1 where id = ?2")
	@Modifying
	void updateFixedAreaId(FixedArea fixedArea,String sid);

	@Query("select s from SubArea s join s.fixedArea f where f.id = ?1")
	List<SubArea> watchSubArea(String id);
	

}
