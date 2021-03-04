package com.xiao.domain;

import com.xiao.pojo.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UploaderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 新增
     * @param uploader
     */
    public void insert(UploadFile uploader) {
        String sql = "INSERT INTO `sys_upload_file`(`uf_id`, `uf_original_file_name`, `uf_saved_file_name`, `uf_ext`, `uf_size`, `uf_related_business`, `uf_remark`, `uf_absolute_path`,`uf_public_link`, `uf_create_time`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?);";

        jdbcTemplate.update(sql, uploader.getId(),
                uploader.getOriginalFileName(),
                uploader.getSavedFileName(),
                uploader.getExt(),
                uploader.getSize(),
                uploader.getRelatedBusiness(),
                uploader.getRemark(),
                uploader.getAbsolutePath(),
                uploader.getPublicLink(),
                uploader.getCreateTime());
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id) {
        String sql = "delete from sys_upload_file where uf_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * 根据ID 查询
     * @param id
     * @return
     */
    public UploadFile selectById(Long id) {

        String sql = "select * from sys_upload_file where  uf_id = ?";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, id);
        while (sqlRowSet.next()) {
            return convert(sqlRowSet);
        }
        return null;
    }

    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @param fileNameContain
     * @return
     */
    public List<UploadFile> selectByPage(int pageIndex, int pageSize, String fileNameContain) {

        String sql = "select * from sys_upload_file where uf_original_file_name like ? order by uf_id desc limit ?,?";

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, "%" + fileNameContain + "%", (pageIndex - 1) * pageSize, pageSize);
        List<UploadFile> uploaderList = new ArrayList<>();
        while (sqlRowSet.next()) {
            uploaderList.add(convert(sqlRowSet));
        }
        return uploaderList;
    }

    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<UploadFile> selectByPage(int pageIndex, int pageSize) {
        String sql = "select * from sys_upload_file order by uf_id desc limit ?,?  ";

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, (pageIndex - 1) * pageSize, pageSize);
        List<UploadFile> uploaderList = new ArrayList<>();
        while (sqlRowSet.next()) {
            uploaderList.add(convert(sqlRowSet));
        }
        return uploaderList;
    }

    /**
     * 数量
     * @param fileNameContain
     * @return
     */
    public Integer selectCount(String fileNameContain){

        String sql = "select count(*) from sys_upload_file ";
        ArrayList<String> objects = new ArrayList<>();
        if(!StringUtils.isEmpty(fileNameContain)){
            sql += " where uf_original_file_name like ?; ";
            objects.add("%"+fileNameContain+"%");
        }

        Integer integer = jdbcTemplate.queryForObject(sql,objects.toArray(), Integer.class);
        if(integer != null)
            return integer;
        return Integer.valueOf(0);
    }

    private UploadFile convert(SqlRowSet sqlRowSet){
        return new UploadFile(sqlRowSet.getLong("uf_id"),
                sqlRowSet.getString("uf_original_file_name"),
                sqlRowSet.getString("uf_saved_file_name"),
                sqlRowSet.getString("uf_ext"),
                sqlRowSet.getLong("uf_size"),
                sqlRowSet.getString("uf_related_business"),
                sqlRowSet.getString("uf_remark"),
                sqlRowSet.getString("uf_absolute_path"),
                sqlRowSet.getString("uf_public_link"),
                sqlRowSet.getTimestamp("uf_create_time")

        );
    }


}
