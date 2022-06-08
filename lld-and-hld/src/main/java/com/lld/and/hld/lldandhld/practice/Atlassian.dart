Atlassian has a number of different products, each containing different types of content. As an example, let’s look at three of our products:

Jira - Issues(ProductID - FeatureID)

Confluence - Pages(ProductID - FeatureID)

Bitbucket - Pull Requests(ProductID - FeatureID)

We want to build a system that allows users to tag content from different products, and then to view content by tags. A goal of this system is that it should be built in a product-agnostic way, so that we could add new products in the future without a large amount of work.

There are three key experiences that we want to build here:

As a user I can add, remove, and update tags on content

Solution:
CRUD operations with tag

Service Name:
    - tag-service
        - Create Tag API
            - HTTP method POST
            - URL  /tag-service/api/v1/tag
            - body in the form of json
            {
                name: "apple",
                productId: 1245,
                documentId: 1323,
            }

        - Update Tag API
            - HTTP method PUT
            - URL  /tag-service/api/v1/tag
            - body in the form of json
            {
                name: "apple",
                productId: 1245,
                documentId: 1323,
            }


Database: Atlassian
    - tables
        - Products
            - id, name, description, 
        - Features
            - id, name, productId, 
        - Tag
            - id, name, description, createdTime, updatedTime, 
        - TagData
            - id, tagId, productId, documentId, createdTime, updatedTime, createdBy, updatedBy
        - PopularTags
            - id, tagId, count, offset, lastTimeUpdated
        


// issueId, pageId,  prId,

As a user, I can click on a tag and see all content that has been associated with that tag
Solution:
Search API
    - URL - /tag-service/api/v1/search?tag=tageName&pageSize=10&pageNumber=1
    - HTTP method GET 
    - Response in the form of JSON:
    [{
        tag
    },{

    }]

As a user, I can see a dashboard of popular tags
Solution:
Popular Tags
    - URL - /tag-service/api/v1/search?tag=tageName&pageSize=10&pageNumber=1
    - HTTP method GET 
    - Response in the form of JSON:

Imagine that you are on a team that needs to design and build the system to power this experience.