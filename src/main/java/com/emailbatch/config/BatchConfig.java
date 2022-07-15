package com.emailbatch.config;

import com.emailbatch.batch.EmailItemProcessor;
import com.emailbatch.batch.EmailItemWriter;
import com.emailbatch.model.dto.EmailDTO;
import com.emailbatch.model.mapper.EmailItemRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static com.emailbatch.util.Constants.JOB_NAME;
import static com.emailbatch.util.Constants.STEP_NAME;
import static com.emailbatch.util.Queries.GET_EMAILS_FOR_MAILING;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;

    @Bean
    public Job emailSenderJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(emailSenderStep())
                .build();
    }

    @Bean
    public Step emailSenderStep() {
        return stepBuilderFactory.get(STEP_NAME).
                <EmailDTO, EmailDTO>chunk(100)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ItemReader<EmailDTO> itemReader() {
        JdbcCursorItemReader<EmailDTO> itemReader = new JdbcCursorItemReaderBuilder<EmailDTO>()
                .name("itemReader")
                .dataSource(dataSource)
                .sql(GET_EMAILS_FOR_MAILING)
                .rowMapper(new EmailItemRowMapper())
                .build();
        return new JdbcCursorItemReaderBuilder<EmailDTO>()
                .name("itemReader")
                .dataSource(dataSource)
                .sql(GET_EMAILS_FOR_MAILING)
                .rowMapper(new EmailItemRowMapper())
                .build();
    }

    @Bean
    public ItemProcessor<EmailDTO, EmailDTO> itemProcessor() {
        return new EmailItemProcessor();
    }

    @Bean
    ItemWriter<EmailDTO> itemWriter() {
        return new EmailItemWriter();
    }

}
