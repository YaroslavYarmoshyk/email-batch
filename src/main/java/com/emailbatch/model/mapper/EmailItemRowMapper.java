package com.emailbatch.model.mapper;

import com.emailbatch.model.dto.EmailDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class EmailItemRowMapper implements RowMapper<EmailDTO> {
    @Override
    public EmailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return EmailDTO.builder()
                .email(rs.getString("email"))
                .build();
    }
}
