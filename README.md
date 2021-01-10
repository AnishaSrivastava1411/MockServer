# <ul>    CommerceIQ Home Assignment</ul>

# MockServer 
 Built a REST based JSON mock server to easily add, update, delete and access data from a JSON file.
    MockServer supports multiple type of entities. An entity_type is a list of entities which are similar by their type.
    
    
# CommerceIQ_MockServer
<h3>1. store.json</h3>
Entities are stored in store.json file. which initially is and empty json object. With every new object added/modified/deleted, the store.json file gets updated.

<h3>2. MockServer.log</h3>
This file contains the log of complete process. Starting from empty json object to entire process shown further.

# URLs for Operations

<h2> 1. Add a record (POST)</h2>

<h3>a) posts : {BASE_URL}/setPosts</h3>

```http://localhost:8081/setPosts```

<h3>Response</h3>

```


{
    "ResponseCode": "200",
    "ResponseMessage": "Data update success"
}
```


<h3>b) author : {BASE_URL}/setAuthor</h3>

```http://localhost:8081/setAuthor```

<h3>Response</h3>


```
{
    "ResponseCode": "200",
    "ResponseMessage": "Data update success"
}
```






<h2>2. Update a record (PUT)</h2>

<h3>a) posts : {BASE_URL}/updatePosts/{posts_id} </h3>

``` http://localhost:8081/updatePosts/2```


```
{
    "ResponseCode": "200",
    "Response Message": "Posts Updated Successfully"
}
```


<h3>Input</h3>


``` 
  {
       "reviews": 256,
       "author": "Shiv Khera",
       "id": 2,
       "title": "You can win",
       "views": 1000
  } 
  ```
  
<h3>Response</h3>

```
{
    "reviews": 512,
    "author": "Shiv ",
    "id": 2,
    "title": "You Can Win",
    "views": 1000
}
```


<h3>b) author : {BASE_URL}/updateAuthor/{author_id} </h3>


```http://localhost:8081/updateAuthor/3```


```
{
    "ResponseCode": "200",
    "Response Message": "Author Updated Successfully"
}
```


<h3>Input</h3>

```
 {
     "first_Name": "New",
     "last_Name": "Delhi",
     "id": 3,
     "posts": 75
 }

```


<h3>Response</h3>


```
{
    "first_Name": "South",
    "last_Name": "India ",
    "id": 3,
    "posts": 1000
}
```




<h2>3. Delete a record (DELETE)</h2>

<h3>a) posts : {BASE_URL}/deletePosts/{posts_id}  </h3>


```http://localhost:8081/deletePosts/2```


<h3>Response</h3>

```
{
    "ResponseCode": "200",
    "Response Message": "Posts Deleted Successfully"
}
```




<h3>b) author : {BASE_URL}/deleteAuthor/{author_id} </h3>


```http://localhost:8081/deleteAuthor/3```

<h3>Response</h3>

```
{
    "ResponseCode": "200",
    "Response Message": "Author Deleted Successfully"
}
```




<h2>4. Get a record  (GET)</h2>



<h3>a) posts : {BASE_URL}/getPosts/{posts_id}  </h3>




```http://localhost:8081/getPosts/2```




<h3>Response</h3>



```Not Found```






```http://localhost:8081/getPosts/1```





<h3>Response</h3>





```
{
    "reviews": 558,
    "author": "CIQ",
    "id": 1,
    "title": "title1",
    "views": 750
}




```


<h3>b) author : {BASE_URL}/getAuthor/{author_id} </h3>




```http://localhost:8081/getAuthor/2```



<h3>Response</h3>



```
{
    "first_Name": "Boomerang",
    "last_Name": "Commerce",
    "id": 2,
    "posts": 39
}

```



<h2>5. Get all records of each entity(GET)</h2>



<h3>a) posts : {BASE_URL}/getAllPostData </h3>



```http://localhost:8081/getAllPostsData```


<h3>Response</h3>



```[
    {
        "reviews": 558,
        "author": "CIQ",
        "id": 1,
        "title": "title1",
        "views": 750
    },
    {
        "reviews": 256,
        "author": "Shiv Khera",
        "id": 2,
        "title": "You can win",
        "views": 1000
    }
]
```





<h3>b) author : {BASE_URL}/getAllAuthorData </h3>



```http://localhost:8081/getAllAuthorData```




<h3>Response</h3>




```
[
    {
        "first_Name": "Commerce",
        "last_Name": "IQ",
        "id": 1,
        "posts": 45
    },
    {
        "first_Name": "Boomerang",
        "last_Name": "Commerce",
        "id": 2,
        "posts": 39
    },
    {
        "first_Name": "New",
        "last_Name": "Delhi",
        "id": 3,
        "posts": 75
    }
]

```


<h2>6. Get all records in a go (GET)</h2>



   <h3>{BASE_URL}/getAll </h3>
   
   
   
 ``` http://localhost:8081/getAll ```  
 
 
   
   
   
 <h3>Response</h3>  
 
 
 
   ```
   
  {
    "posts": [
        {
            "reviews": 558,
            "author": "CIQ",
            "id": 1,
            "title": "title1",
            "views": 750
        },
        {
            "reviews": 256,
            "author": "Shiv Khera",
            "id": 2,
            "title": "You can win",
            "views": 1000
        }
    ],
    "authors": [
        {
            "first_Name": "Commerce",
            "last_Name": "IQ",
            "id": 1,
            "posts": 45
        },
        {
            "first_Name": "Boomerang",
            "last_Name": "Commerce",
            "id": 2,
            "posts": 39
        },
        {
            "first_Name": "New",
            "last_Name": "Delhi",
            "id": 3,
            "posts": 75
        }
    ]
}

```




   
<h2> 7.  Filtering at entity level (GET) </h2>



<h3>a) posts : {BASE_URL}/posts?title=title1&author=CIQ </h3>



```http://localhost:8081/getFilterPosts?title=title1&author=CIQ```



 <h3>Response</h3> 
 
 
 
 ```
 {
    "reviews": 558,
    "author": "CIQ",
    "id": 1,
    "title": "title1",
    "views": 750
}

```



<h3>b) author : {BASE_URL}/author?first_Name=Commerce&last_Name=IQ  </h3>



```http://localhost:8081/getFilterAuthor?first_Name=Commerce&last_Name=IQ```



 <h3>Response</h3> 
 
 

```
{
    "first_Name": "Commerce",
    "last_Name": "IQ",
    "id": 1,
    "posts": 45
}

```




<h2> 8. Sorting at entity level (GET) </h2>
 
 
 <h3>a) posts : {BASE_URL}/posts?_sort=views&_order=asc  </h3>
 
 
 ```http://localhost:8081/getSortedPosts?_sort=views&_order=asc```


 <h3>Response</h3> 
 
 
 ```
 [
    {
        "reviews": 558,
        "author": "CIQ",
        "id": 1,
        "title": "title1",
        "views": 750
    }
]
 
 ```




 <h3>b) author : {BASE_URL}/author?_sort=posts&_order=asc  </h3>
 
 
 ```http://localhost:8081/getSortedAuthor?_sort=posts&_order=asc```
 
 
 
  <h3>Response</h3> 
  
  
 
 ```
 [
      
        {
            "first_Name": "Boomerang",
            "last_Name": "Commerce",
            "id": 2,
            "posts": 39
        },
          {
            "first_Name": "Commerce",
            "last_Name": "IQ",
            "id": 1,
            "posts": 45
        }
 ]
 
 ```


