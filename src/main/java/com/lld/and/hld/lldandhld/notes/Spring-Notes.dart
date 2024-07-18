Spring Notes;

- Wiring in Spring: @Autowired, @Resource and @Inject - https://www.baeldung.com/spring-annotations-resource-inject-autowire
- 



- Application Layers
    - Controller -> Service(Business logic and validation) -> DAO(Check data existence in DB)  -> Repository (Operate on DB)

- Bean priority 
    - on the basis of value specified in @Order annotation, pick the one with highest precedence. by default it has LOWER_PRECEDENCE
    - @Order()

Defining Beans
    - class with @Configuration annotation
        - define methods with @Bean annotation.
    - class with @Component/@Service/@Repository annotations



Ways to inject dependencies:
    - Field Injection 
        Ex: 
        @Autowired || @Autowired(name="namedBean") || @Autowired @Qualifier("namedBean")
        private NewClass object;

    - Setter Injection

        Ex:
        private NewClass object;

        @Autowired || @Autowired(name="namedBean") || @Autowired @Qualifier("namedBean")
        public void setObject(NewClass object) {
            this.object = object;
        }

    - Constructor

        class NewClass {

            @Autowired || @Autowired(name="namedBean") || @Autowired @Qualifier("namedBean")
            public NewClass(NewClass object) {
                this.object = object;
            }
        }


- Wiring dependencies annotations:
    - @Resource
        - List by precedence:   
            - During run time, dependency will resolve in this order
                - Match by name 
                    - Define bean with name - @Bean(name = "namedBean"), @Resource(name="namedBean") 

                - Match by type 
                    - Do not mention the name while defining beans, so it will resolve dependencies by type.

                - Match by qualifier 
                    - Define bean with name - @Bean(name = "namedBean"), while wiring use Qualifier annotation
                        Ex:
                        @Resource
                        @Qualifier("namedBean")

    - @Inject - Import dependency javax.inject
        - List by precedence:   
            - Match by type
                - Do not mention the name while defining beans, so it will resolve dependencies by type.

            - Match by qualifier 
                - Define bean with name - @Bean(name = "namedBean"), while wiring use Qualifier annotation
                    Ex:
                    @Inject
                    @Qualifier("namedBean")

            - Match by name 
                - Define bean with name - @Bean(name = "namedBean"), @Inject @Named("namedBean")

    - @Autowired
        - Match by type
            - Do not mention the name while defining beans, so it will resolve dependencies by type.

        - Match by qualifier 
            - Define bean with name - @Bean, while wiring use Qualifier annotation with bean function name
                Ex:
                @Bean 
                Class namedMethod() {
                    return new Class();
                }

                @Autowired
                @Qualifier("namedMethod")

        - Match by name 
            - Define Component with value - @Component(value = "matchByName"), then use the same value for field name;
            Ex:
            @Component(value = "matchByName")
            Class NewClass {

            }


            @Autowired
            NewClass matchByName;



- @Autowired (in depth understanding - https://www.baeldung.com/spring-autowire)

    - If some of the beans are not required and to avoid application failure can use required=false

        Ex: @Autowired(required=false)
    
    - Match by qualifier
        - 
    
    - Create Custom qualifier 





- Placeholders 
    - ${key}  
    - to read in code, use @Value("${key}") under the @Configuration class
    - We can use Placeholders in application.properties files to bind into some properties from vm args.

- Separting profiles in single application.properties file.
    - use #---
    Ex:
    common properties
    #---
    spring.config.activate.on-profile=dev
    ..
    ..
    #---
    spring.config.activate.on-profile=prod


- Interceptors
 - Implement HandlerInterceptor interface, then register the implemented class in WebMvcConfigurerAdapter.
 









