package com.emailbatch.batch;

import com.emailbatch.model.dto.EmailDTO;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class EmailItemWriter implements ItemWriter<EmailDTO> {

    @Override
    public void write(List<? extends EmailDTO> list) throws Exception {

    }
}
