package com.nsr.movieinfo;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import com.nsr.movieinfo.configure.Request;
import com.nsr.movieinfo.model.Movie;


//@EnableEurekaClient
public class MovieInfoApplication {

	public Object handleRequest(Request request, Context context){
		AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.standard().withRegion("us-east-2").build();
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		Movie movie = null;
	    
		if(request.getHttpMethod().equals("GET"))
		{
			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		    scanExpression.addFilterCondition("movieName", new Condition()                                           
		       .withComparisonOperator(ComparisonOperator.EQ)                                                
		       .withAttributeValueList(new AttributeValue().withS(request.getMovieName())));
		    PaginatedScanList<Movie> list= mapper.scan(Movie.class, scanExpression);
		    movie=list.get(0);
		    if (movie == null) {
				throw new ResourceNotFoundException("Resource Not Found "+request.getMovieName());
			}
			return movie;
		
		}
		else {
			movie = request.getMovie();
			mapper.save(movie);
			return movie;
		
		}
	
	}
}

