# Contributing Manual

TODO: LINK CTRL+F

We love your input! We want to make contributing to this project as easy and transparent as possible, whether it's:

1. Reporting issues or suggesting improvements through [Issues](#flow-for-issue).
2. Contributing code through [Pull Requests](#flow-for-code-contributing).

## Flow for Code Contributing

This Flow is Based Github Flow, with workflow based [Feature branch](https://www.optimizely.com/optimization-glossary/feature-branch) So All Code Changes Happen Through Pull Requests.

0. Create issue with details of your contribution, such as motivation and changes.
    - 0.1 If possible create [task list](https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/about-task-lists) of changes.
1. **Fork** the repo.
2. Create new **branch** for your contribution.
    - 2.1 create branch from `master`.
    - 2.2 with name `'releases/issue-{number_issue}'`.
```
git checkout -b releases/issue-{number_issue}
```
3. Make your code changes.
    3.1 Ensure your code adheres to the project's style guidelines.
    3.2 If you've added code that should be tested, add tests.
    3.3. Ensure the test suite passes.
4. Commit your changes with clear messages.
5. Push to your fork, and send pull_request from `releases/issue-{number_issue}` -> `releases/issue-{number_issue}`.
6. If all goes well, the maintainer will **squash and merge and close your issue**.

## Flow for [Issue](https://github.com/gulybyte/sample-todo/issues)
We use GitHub issues to track public bugs. Report a bug by [opening a new issue](https://github.com/gulybyte/sample-todo/issues/new/choose); it's that easy!

## Code of Conduct
Please read and adhere to our [Code of Conduct](/CODE_OF_CONDUCT.md). We aim to maintain a respectful and welcoming environment for all contributors.

## Any contributions you make will be under the Licence Creative Commons Legal Code (CC0 1.0 Universal)
By contributing, you agree that your contributions will be licensed under its [Creative Commons Legal Code (CC0 1.0 Universal)](https://github.com/gulybyte/sample-todo/blob/main/LICENCE).

# Contributors
[gulybyte](https://github.com/gulybyte) maintainer project

<a href="https://github.com/gulybyte/sample-todo/graphs/contributors"><img src="https://contributors-img.web.app/image?repo=gulybyte/sample-todo&max=500" alt="List of Contributors"/></a>
