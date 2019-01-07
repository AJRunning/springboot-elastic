package com.runningsss.elastic.repository;

import com.runningsss.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liqings
 * @date 2018-12-21
 */
@Repository
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {

}
