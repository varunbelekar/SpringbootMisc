package com.graphql.springbootgraphql.controller;

import com.graphql.springbootgraphql.model.Author;
import com.graphql.springbootgraphql.model.Book;
import graphql.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController{

    @QueryMapping
    public Book bookById(@Argument String id){
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book){
        return Author.getById(book.getAuthorId());
    }
}
