# eshop


#### Quick Access
- [Tutorial 1](#tutorial-1)

- [Tutorial 2](#tutorial-2)

- [Tutorial 3](#tutorial-3)

# Tutorial 1

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

# Tutorial 2

## Reflection

1. 

Code Qualities issues that I encounter are, 
- multiple unused imports, I removed unused imports for maintainability purposes,
- using throw new Exception class, I removed them to make the codes cleaner,
```java
@Test
void productListPage_isDisplayed(ChromeDriver driver) throws Exception{
    driver.get(baseUrl + "/product/list");
    assertPageTitle(driver, "Product List");
}
```

```java
@Test
void productListPage_isDisplayed(ChromeDriver driver){ // remove throwing exception
    driver.get(baseUrl + "/product/list");
    assertPageTitle(driver, "Product List");
}
```
- Autowired, it is better to use Autowired like this to be sure that Service is specified correctly
```java 
    private final ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }
```

There's not much issues regarding code quality other than needing someone else to review the code to ensure maintainability, which I can't really fix :D. SonarCloud really helped me spot this issues, but I'm using the free version that I have to push to main to obtain the results.


2.

Yes, the CI implementation already automatically builds, tests, and analyzes the code on push/pull requests. The workflows run multiple quality checks such as unit tests, SonarQube analysis, and even supply-chain security with scorecard, ensuring that the codebase remains healthy and maintainable. The CD is configured using koyeb, on every push to main branch, it automatically deploy the processes to production. The automation helps developers spotting issues and fix them to prevent broken codes being released to production or unmaintainable codes unintentionally left in the codebase.  In conclusion, the CI/CD implementation has met the definition of Continuous Integration and Continuous Deployment. 

# Tutorial 3

> 1. Explain what principles you apply to your project!

- **Single Responsibility Principle**: By separating each class into different files, I make sure that each class has only one responsibility. 
    - CarController was extending ProductController, but CarController does not have anything to do with Product. ProductController has the responsibility to product-related requests while the Car Controller has the responsibility to car-related requests. 
    - Repository, Service, Model, and Controller each have a single responsibilty. Repository to handle database related operations, Servince to handle business logics operations, Model to define the object entity, and Controller to Control requests. 
- **Open Closed Principle**: The software artifact should be open for extension but closed to modification.
    - I implemented the basic functions on Car, we don't need to add the same function on other class that inherits it. The update function also added to reduce complexity on service layer and closed to modification. 
- **Dependancy Inversion Principle**: we should use abstraction over concrete implementations, classes shouldn't depend to the modules directly; instead, should depend on abstraction, being independent to the specific details. 
    - I changed CarController to depend on the interface instead of directly to the implementation of CarService. 
    - I abstracted CarRepository and ProductRepository so the Service doesn't depend directly to the modules.

> 2.  Explain the advantages of applying SOLID principles to your project with examples.

- The code is easier to maintain. By applying SRP, every class has only one responsibility. If I wanted to change the business logic, I know that I have to modify Service. I don't need to change other classes because it holds different responsibility. 

- The code is easier to test. Depending on abstraction allows us to subtitute real components with mocks during testing. In this Project, CarController depends on a CarService interface, we can easily inject a mock CarService when writing unit tests. This makes the tests more focused and easier to maintain. 

- The code is more flexible. By applying OCP, when a new functionality needs to be added to the Car Domain such as adding behaviours, we can extend the existing implementation without altering tested and stable code, reducing the risk of introducing bugs.

> 3. Explain the disadvantages of not applying SOLID principles to your project with examples.

- SOLID principles lead to an increase in the number of classes and interfaces. The codebase became more complex and harder to navigate. In our code, a single request has to navigate through Controller, Service, and Repository. We could just squash them into a single function, but the code is harder to maintain.

- Developers should think more during development. Strictly applying SOLID principles requires more upfront planning, additional interfaces, and wiring between components, which might slow down development time.  