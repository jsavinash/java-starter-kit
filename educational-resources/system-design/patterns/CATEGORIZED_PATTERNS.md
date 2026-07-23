# Design Patterns - Categorized Overview

This document lists all design patterns organized by their respective categories.

---

## 🏗️ Creational
Patterns that deal with object creation mechanisms.

| Pattern | Description |
|---------|-------------|
| [abstract-factory](abstract-factory) | Provides an interface for creating families of related objects |
| [builder](builder) | Separates object construction from its representation |
| [dependency-injection](dependency-injection) | Injects dependencies into objects rather than creating them |
| [factory](factory) | Creates objects without exposing the instantiation logic |
| [factory-kit](factory-kit) | A configurable factory of factories |
| [factory-method](factory-method) | Defines an interface for creating an object, but lets subclasses decide which class to instantiate |
| [monostate](monostate) | Achieves singleton-like behavior through shared state |
| [multiton](multiton) | Ensures a class has only named instances |
| [object-pool](object-pool) | Reuses objects that are expensive to create |
| [prototype](prototype) | Creates new objects by copying a prototype instance |
| [registry](registry) | A central registry for objects |
| [singleton](singleton) | Ensures a class has only one instance |
| [step-builder](step-builder) | Builds objects step by step with a fluent interface |
| [type-object](type-object) | Creates flexible and extensible types |

---

## 🏛️ Structural
Patterns that deal with object composition and relationships.

| Pattern | Description |
|---------|-------------|
| [abstract-document](abstract-document) | Manages dynamic properties in a flexible manner |
| [adapter](adapter) | Allows incompatible interfaces to work together |
| [bridge](bridge) | Decouples an abstraction from its implementation |
| [business-delegate](business-delegate) | Decouples presentation from business services |
| [component](component) | Simplifies complex systems with reusable components (ECS architecture) |
| [composite](composite) | Composes objects into tree structures |
| [composite-entity](composite-entity) | Manages a set of interrelated persistent objects |
| [composite-view](composite-view) | Composes views from smaller reusable components |
| [converter](converter) | Converts between different object representations |
| [curiously-recurring-template-pattern](curiously-recurring-template-pattern) | Uses CRTP for static polymorphism |
| [dao-factory](dao-factory) | Factory for creating Data Access Objects |
| [data-access-object](data-access-object) | Abstracts data persistence operations |
| [data-transfer-object](data-transfer-object) | Transfers data between subsystems |
| [decorator](decorator) | Adds behavior to objects dynamically |
| [domain-model](domain-model) | Models business concepts as objects |
| [dynamic-proxy](dynamic-proxy) | Creates proxy instances dynamically |
| [extension-objects](extension-objects) | Adds functionality to objects without modifying them |
| [facade](facade) | Provides a simplified interface to a complex subsystem |
| [flyweight](flyweight) | Shares objects to support large numbers of fine-grained objects |
| [marker-interface](marker-interface) | Uses empty interfaces to mark classes with metadata |
| [money](money) | Represents monetary values with proper arithmetic |
| [parameter-object](parameter-object) | Groups method parameters into objects |
| [private-class-data](private-class-data) | Restricts access to class data |
| [proxy](proxy) | Provides a surrogate for another object |
| [role-object](role-object) | Dynamically adds roles to objects |
| [separated-interface](separated-interface) | Separates interface definition from implementation |
| [servant](servant) | Provides common behavior to a group of classes |
| [service-locator](service-locator) | Abstracts service lookup and retrieval |
| [session-facade](session-facade) | Provides a unified interface to a set of services |
| [spatial-partition](spatial-partition) | Efficiently manages spatial objects |
| [special-case](special-case) | Handles special cases without conditionals |
| [strangler](strangler) | Incrementally migrates legacy systems |
| [twin](twin) | Simulates multiple inheritance in single-inheritance languages |
| [value-object](value-object) | Represents immutable values |
| [virtual-proxy](virtual-proxy) | Defers object creation until needed |

---

## 🎭 Behavioral
Patterns that deal with object communication and responsibility.

| Pattern | Description |
|---------|-------------|
| [acyclic-visitor](acyclic-visitor) | Extends visitor pattern without cyclic dependencies |
| [bytecode](bytecode) | Encodes behavior as bytecode instructions |
| [chain-of-responsibility](chain-of-responsibility) | Passes requests along a chain of handlers |
| [client-session](client-session) | Manages client state across requests |
| [collecting-parameter](collecting-parameter) | Collects parameters across multiple method calls |
| [command](command) | Encapsulates a request as an object |
| [commander](commander) | Executes commands with retry and fallback |
| [context-object](context-object) | Encapsulates system state and behavior |
| [data-mapper](data-mapper) | Maps between objects and data stores |
| [delegation](delegation) | Delegates responsibilities to helper objects |
| [dirty-flag](dirty-flag) | Tracks changes to avoid unnecessary operations |
| [double-buffer](double-buffer) | Swaps buffers for smooth state updates |
| [double-dispatch](double-dispatch) | Selects methods based on runtime types of multiple objects |
| [execute-around](execute-around) | Manages resource setup and cleanup |
| [feature-toggle](feature-toggle) | Enables/disables features at runtime |
| [filterer](filterer) | Filters collections with flexible criteria |
| [fluent-interface](fluent-interface) | Provides fluent method chaining |
| [game-loop](game-loop) | Manages game state updates and rendering |
| [health-check](health-check) | Monitors system health |
| [identity-map](identity-map) | Ensures each object is loaded only once |
| [interpreter](interpreter) | Evaluates language expressions |
| [iterator](iterator) | Provides sequential access to collection elements |
| [mediator](mediator) | Reduces coupling between communicating objects |
| [memento](memento) | Captures and restores object state |
| [mute-idiom](mute-idiom) | Silently handles exceptions |
| [notification](notification) | Collects and manages notifications |
| [null-object](null-object) | Provides a default no-op object |
| [observer](observer) | Notifies dependents of state changes |
| [partial-response](partial-response) | Returns partial responses from services |
| [pipeline](pipeline) | Processes data through a sequence of stages |
| [property](property) | Manages dynamic properties on objects |
| [rate-limiting-pattern](rate-limiting-pattern) | Controls request rates |
| [specification](specification) | Combines business rules with boolean logic |
| [state](state) | Alters behavior based on internal state |
| [strategy](strategy) | Selects algorithms at runtime |
| [subclass-sandbox](subclass-sandbox) | Defines behavior in subclasses using provided operations |
| [template-method](template-method) | Defines algorithm skeleton, lets subclasses fill steps |
| [templateview](templateview) | Renders views using templates |
| [update-method](update-method) | Updates game entities per frame |
| [visitor](visitor) | Separates algorithms from objects they operate on |

---

## ⚡ Concurrency
Patterns that deal with multi-threading and concurrent execution.

| Pattern | Description |
|---------|-------------|
| [active-object](active-object) | Decouples method execution from method invocation |
| [actor-model](actor-model) | Manages concurrent computation via actors |
| [async-method-invocation](async-method-invocation) | Invokes methods asynchronously |
| [backpressure](backpressure) | Controls data flow in reactive systems |
| [balking](balking) | Prevents actions when object is in wrong state |
| [double-checked-locking](double-checked-locking) | Reduces locking overhead |
| [event-based-asynchronous](event-based-asynchronous) | Handles events asynchronously |
| [event-queue](event-queue) | Manages event processing order |
| [fanout-fanin](fanout-fanin) | Parallelizes tasks and aggregates results |
| [guarded-suspension](guarded-suspension) | Suspends operations until condition is met |
| [half-sync-half-async](half-sync-half-async) | Combines synchronous and asynchronous processing |
| [leader-election](leader-election) | Elects a leader among distributed nodes |
| [leader-followers](leader-followers) | Efficient thread pool model |
| [lockable-object](lockable-object) | Provides locking mechanism for objects |
| [master-worker](master-worker) | Distributes work among workers |
| [monitor](monitor) | Synchronizes access to shared resources |
| [poison-pill](poison-pill) | Signals shutdown in producer-consumer |
| [producer-consumer](producer-consumer) | Coordinates producers and consumers |
| [promise](promise) | Represents a future result |
| [reactor](reactor) | Handles service requests concurrently |
| [thread-pool-executor](thread-pool-executor) | Manages a pool of worker threads |
| [thread-specific-storage](thread-specific-storage) | Provides thread-local storage |

---

## 🏗️ Architectural
Patterns that define high-level system architecture.

| Pattern | Description |
|---------|-------------|
| [bloc](bloc) | Business Logic Component for state management |
| [clean-architecture](clean-architecture) | Separates concerns into concentric layers |
| [command-query-responsibility-segregation](command-query-responsibility-segregation) | Separates read and write operations |
| [event-driven-architecture](event-driven-architecture) | Decouples components via events |
| [event-sourcing](event-sourcing) | Stores state changes as events |
| [flux](flux) | Unidirectional data flow architecture |
| [front-controller](front-controller) | Centralizes request handling |
| [hexagonal-architecture](hexagonal-architecture) | Isolates core logic from external concerns |
| [intercepting-filter](intercepting-filter) | Filters requests and responses |
| [layered-architecture](layered-architecture) | Organizes code into layers |
| [microservices-aggregrator](microservices-aggregrator) | Aggregates responses from multiple services |
| [microservices-client-side-ui-composition](microservices-client-side-ui-composition) | Composes UI from multiple services |
| [microservices-distributed-tracing](microservices-distributed-tracing) | Traces requests across services |
| [model-view-controller](model-view-controller) | Separates data, UI, and control logic |
| [model-view-intent](model-view-intent) | Unidirectional data flow with intents |
| [model-view-presenter](model-view-presenter) | Separates presentation from view |
| [model-view-viewmodel](model-view-viewmodel) | Binds view to view model |
| [monolithic-architecture](monolithic-architecture) | Single-tiered software architecture |
| [naked-objects](naked-objects) | Exposes domain objects directly |
| [page-controller](page-controller) | Handles page-specific requests |
| [polling-publisher](polling-publisher) | Polls for updates and publishes |
| [presentation-model](presentation-model) | Separates UI logic from view |
| [service-layer](service-layer) | Defines application boundary |
| [service-to-worker](service-to-worker) | Coordinates service and view |
| [view-helper](view-helper) | Encapsulates view logic |

---

## 🗄️ Data Access
Patterns that deal with data persistence and retrieval.

| Pattern | Description |
|---------|-------------|
| [metadata-mapping](metadata-mapping) | Maps object metadata to database |
| [optimistic-offline-lock](optimistic-offline-lock) | Prevents concurrent data conflicts |
| [repository](repository) | Mediates between domain and data layers |
| [serialized-entity](serialized-entity) | Streamlines data persistence and exchange |
| [serialized-lob](serialized-lob) | Stores large objects as serialized data |
| [sharding](sharding) | Partitions data across databases |
| [single-table-inheritance](single-table-inheritance) | Maps inheritance to a single table |
| [table-inheritance](table-inheritance) | Maps inheritance to multiple tables |
| [table-module](table-module) | Handles business logic for a table |
| [transaction-script](transaction-script) | Organizes business logic as transactions |
| [unit-of-work](unit-of-work) | Tracks changes for batch operations |
| [version-number](version-number) | Manages optimistic concurrency with versions |

---

## 🔄 Functional
Patterns that leverage functional programming concepts.

| Pattern | Description |
|---------|-------------|
| [callback](callback) | Passes executable code as an argument |
| [collection-pipeline](collection-pipeline) | Processes collections with functional operations |
| [combinator](combinator) | Combines functions to build complex operations |
| [currying](currying) | Transforms multi-argument functions |
| [function-composition](function-composition) | Composes functions to create new functions |
| [map-reduce](map-reduce) | Processes large datasets in parallel |
| [monad](monad) | Wraps values with computational context |
| [trampoline](trampoline) | Prevents stack overflow in recursive functions |

---

## 🔌 Integration
Patterns that facilitate system integration.

| Pattern | Description |
|---------|-------------|
| [ambassador](ambassador) | Offloads common connectivity tasks |
| [anti-corruption-layer](anti-corruption-layer) | Prevents domain corruption from external systems |
| [gateway](gateway) | Provides a unified entry point |
| [microservices-api-gateway](microservices-api-gateway) | Routes requests to microservices |
| [microservices-log-aggregation](microservices-log-aggregation) | Aggregates logs from multiple services |

---

## 📨 Messaging
Patterns that deal with message-based communication.

| Pattern | Description |
|---------|-------------|
| [data-bus](data-bus) | Distributes data to multiple consumers |
| [event-aggregator](event-aggregator) | Aggregates events from multiple sources |
| [microservices-idempotent-consumer](microservices-idempotent-consumer) | Ensures idempotent message processing |
| [publish-subscribe](publish-subscribe) | Decouples publishers from subscribers |

---

## ⚡ Performance Optimization
Patterns that improve system performance.

| Pattern | Description |
|---------|-------------|
| [caching](caching) | Stores frequently accessed data |
| [data-locality](data-locality) | Organizes data for cache efficiency |
| [lazy-loading](lazy-loading) | Defers object initialization |

---

## 🛡️ Resilience
Patterns that improve system reliability and fault tolerance.

| Pattern | Description |
|---------|-------------|
| [circuit-breaker](circuit-breaker) | Prevents cascading failures |
| [queue-based-load-leveling](queue-based-load-leveling) | Levels load using queues |
| [retry](retry) | Retries failed operations |
| [saga](saga) | Manages distributed transactions |
| [tolerant-reader](tolerant-reader) | Reads data despite schema changes |

---

## 📦 Resource Management
Patterns that manage system resources.

| Pattern | Description |
|---------|-------------|
| [resource-acquisition-is-initialization](resource-acquisition-is-initialization) | Ties resource lifetime to object lifetime |
| [server-session](server-session) | Manages server-side session state |
| [throttling](throttling) | Controls resource usage |

---

## 🔍 Service Discovery
Patterns for service location and registration.

| Pattern | Description |
|---------|-------------|
| [microservices-self-registration](microservices-self-registration) | Services register themselves with discovery |

---

## 🧪 Testing
Patterns that facilitate testing.

| Pattern | Description |
|---------|-------------|
| [arrange-act-assert](arrange-act-assert) | Structures test cases |
| [object-mother](object-mother) | Creates test objects |
| [page-object](page-object) | Encapsulates page UI in tests |
| [service-stub](service-stub) | Stubs external services in tests |

---

## 💡 Idiom
Common programming idioms.

| Pattern | Description |
|---------|-------------|
| [immutable](immutable) | Creates objects that cannot be modified |

---

> **Total Patterns: 200+** across 16 categories.
> Generated from the [Java Design Patterns](https://github.com/iluwatar/java-design-patterns) repository.