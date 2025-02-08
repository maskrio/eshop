# eshop

## Reflection 1
Clean Code principle, methods should be concise and serves one specific purpose, the code will be easier to maintain and test. Implementation has to be straightforward, self-documenting with meaningfull variable and method names. Bug detections become simpler due to focused functionality and maintenance becomes more managable. 

Controller Methods are used to route the incoming HTTP requests to service methods. Service Methods are used to implement the control business logic in preparation of data handling. Repository Methods are used to Manipulate or get the necessary to the request.

There's a mistake where productId is null once created. ID has to be provided to edit or delete, so I have to update the logic at `ProductServiceImpl` whenever a new product is created, generate an unique ID for it. 

