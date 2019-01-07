package com.runningsss.elastic;

import com.runningsss.elastic.bean.Article;
import com.runningsss.elastic.bean.Book;
import com.runningsss.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticApplicationTests {

	@Autowired
	JestClient jestClient;

	@Autowired
	BookRepository bookRepository;

	@Test
	public void contextLoads() {

		Article article = new Article();
		article.setId(1);
		article.setTitle("hello world!");
		article.setAuthor("李三");
		article.setContent("这是一个好消息呀");

		Index index = new Index.Builder(article).index("runningsss").type("books").build();
		try {
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public  void search(){

		String json="{\n" +
				"    \"query\" : {\n" +
				"        \"match\" : {\n" +
				"            \"content\" : \"好消息\"\n" +
				"        }\n" +
				"    }\n" +
				"}\n";

		Search search = new Search.Builder(json).addIndex("runningsss").addType("books").build();

		try {
			SearchResult result = jestClient.execute(search);
			System.out.println(result.getJsonString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test01(){

		Book book = new Book();
		book.setId(1);
		book.setTitle("三国演义");
		book.setAuthor("罗贯中");

		bookRepository.index(book);
	}



}

