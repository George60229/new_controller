package com.epam.spm.mapper;

import com.epam.spm.model.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag = new Tag();
        tag.setName(rs.getString("name"));
        tag.setId(rs.getInt("tag_id"));
        return tag;
    }
}
