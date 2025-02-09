# eshop

## Reflection 1
Clean Code principle, methods should be concise and serves one specific purpose, the code will be easier to maintain and test. Implementation has to be straightforward, self-documenting with meaningfull variable and method names. Bug detections become simpler due to focused functionality and maintenance becomes more managable. 

Controller Methods are used to route the incoming HTTP requests to service methods. Service Methods are used to implement the control business logic in preparation of data handling. Repository Methods are used to Manipulate or get the necessary to the request.

There's a mistake where productId is null once created. ID has to be provided to edit or delete, so I have to update the logic at `ProductServiceImpl` whenever a new product is created, generate an unique ID for it. 

There's another mistake that I made, which is pushing directly to main, and squashing multiple commits to a branch. I realised this mistake after a read through the document and found the grading criteria. Due to this mistake, I reflect that in the later days, I have to collaborate with others. I cannot do everything as I wanted and have to rely on others. All feature development should take place in a dedicated branch such that any changes to the codebase are tracked in a different feature branch, which will be an advantage for continuous integration, the codebase are managed systematically.

## Reflection 2
1. 
I feel assured that my code runs as intended, and writing unit tests forces me to consider scenarios that might occur in production.

I don't believe there's a fixed number of unit tests required for a class; it really depends on how complex the class is and how much responsibility it holds.

Code coverage helps identify parts of the code that aren't tested, and 100% coverage unit tests ensure that every part of the code is executed during testing. However, this doesn't necessarily mean that the code is free of errors. If tests are poorly written, there may be edge cases that could lead to unintended behavior, which highlights the importance of crafting unit tests carefully to cover all potential edge cases in production.

2. 
If you make a new File and create the same exact setup procedures and instance variables, maintenance will get difficult later due to any changes to the setup code must be replicated across all test classes. The potential improvement is use a shared base class that all test classes extend such that common logic is centralized, easing future modifications and reducing redundancy.