package com.example.mycontact.repository;

import com.example.mycontact.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlockRepositoryTest {
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void crud(){
        Block block = new Block("martin");
        block.setReason("친하지 않음");
        block.setStartDate(LocalDateTime.now());
        block.setEndDate(LocalDateTime.now());

        blockRepository.save(block);

        List<Block> blocks = blockRepository.findAll();

        assertThat(blocks.size()).isEqualTo(3);
        assertThat(blocks.get(0).getName()).isEqualTo("dennis");
        assertThat(blocks.get(1).getName()).isEqualTo("sophia");
        assertThat(blocks.get(2).getName()).isEqualTo("martin");
    }
}