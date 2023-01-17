package com.epam.spm.dao.impl;


import com.epam.spm.dao.TagDAO;
import com.epam.spm.exception.AppNotFoundException;
import com.epam.spm.exception.ErrorCode;
import com.epam.spm.mapper.TagMapper;
import com.epam.spm.model.Tag;
import com.epam.spm.dao.enumeration.CertificateParameters;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagDAOImpl extends EntityDAOImpl implements TagDAO {

    public TagDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public Tag create(Tag tag) {


        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("tages").usingGeneratedKeyColumns("tag_id");

        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put(CertificateParameters.NAME.getMessage(), tag.getName());
        int newId = (int) simpleJdbcInsert.executeAndReturnKey(parameters);
        Tag result = new Tag();
        result.setName(tag.getName());
        result.setId(newId);
        return result;

    }

    @Override
    public List<Tag> getAllTags() {
        String SQL = "select * from tages order by name ASC";
        return jdbcTemplateObject.query(SQL, new TagMapper());
    }

    @Override
    public Tag getById(int id) {
        String SQL = "select * from tages where id=" + id;
        return jdbcTemplateObject.queryForObject(SQL, new TagMapper());
    }


    @Override
    public List<Tag> getEntityByName(String name) {
        String SQL = "select * from tages where name='" + name + "'";
        List<Tag> result = jdbcTemplateObject.query(SQL, new TagMapper());
        if (result.size() == 0) {
            throw new AppNotFoundException("Tag with this name is not found" + name, ErrorCode.TAG_NOT_FOUND);
        }
        return result;
    }


    @Override
    public boolean deleteById(Integer id) {
        String SQL = "delete from tages where tag_id=" + id;
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new AppNotFoundException("Tag with this id " + id + " is not deleted ", ErrorCode.TAG_NOT_FOUND);
        }
        return true;
    }

    @Override
    public boolean deleteByName(String name) {
        String SQL = "delete from tages where name='" + name + "'";
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new AppNotFoundException("Tag with this name " + name + " is not deleted ", ErrorCode.TAG_NOT_FOUND);
        }
        return true;
    }
}
