# Rules of Engagement
There is a fine balance to aim for when it comes to the amount of rules and their enforceability. Even more so when dealing with exceptions to said rules. For this reason the list below should be considered as a set of style guides enforced with relative strictness to allow for some leniency.

Besides the obvious Accuracy and Stability of the framework, the three most valued attributes we strive for as being 'quality code' are: _*Readability*_, _*Maintainability*_ and _*Portability*_. Working with common sense we should be able to suffice with a short check-list.

## Check List
- **Strictly Enforced**
  - [ ] Code Indent: No tabs, 2 spaces per indent level
  - [ ] Line Length: 150 chars maximum including indent whitespace
  - [ ] Steps: Glue code should ideally be one line only. Two lines may improve readability, but three lines should be an exception.
  - [ ] Actions: If three 3 lines are insufficient, then the code should be extracted in a separate method. For a few short methods the Step-class itself is fine, but a separate Action class should be considered to keep things tidy and maintainable.
  - [ ] Imports: A wildcard import is used when two or more libraries/classes are imported from the same dependency.
  - [ ] To run a method from another class once it's fine to use an in-place instantiation, if required more than once the class should set a proper variable to instantiate and re-use the class.
  - [ ] Variables intended for re-use should be defined at the top of the file or gathered in a single class for tidyness and ease of maintenance.
- **Succinct and Descriptive**
  - [ ] Ideally both public and private methods are named consistently in a way which describes what will happen.
  - [ ] Extra points are awarded for methods which can be chained and form somewhat of a legible sentence as a result.
- **In line Documentation**
  - [ ] Public methods _*should be*_ documented with at least a short description, even if the name says it all. It's a small investment in the quality of the generated JavaDoc.
  - [ ] Private methods _*may be*_ documented if it adds clarity (f.e: the code isn't self-explanatory enough)
  