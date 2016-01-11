# Branching and Releasing

For serialization projects we use gitflow. You can read more about it below:

  * [Git Flow](http://nvie.com/posts/a-successful-git-branching-model/)
  * [Git Flow Cheat Sheet](https://danielkummer.github.io/git-flow-cheatsheet/)

First you'll have to initialize gitflow for the project, see Configuring git-flow. Once done, just run release.py (see Performing a release)

## Configuring git-flow

### Note: this process only needs to be completed ONCE for the project

If you don't have git-flow, you can install with:

```
brew install git-flow
```

Make sure you have the release branch checked out.

```bash
$ git checkout release
```

Initialize git-flow.

```bash
$ git flow init

Which branch should be used for bringing forth production releases?
Branch name for production releases: [master] release

Which branch should be used for integration of the "next release"?
Branch name for "next release" development: [master] 

How to name your supporting branch prefixes?
Feature branches? [feature/] 
Release branches? [release/] rel/
Hotfix branches? [hotfix/] 
Support branches? [support/] 
Version tag prefix? [] api-
$
```

## Performing a release - Starting from master branch

(1) Check in all of your changes

(2) run release.py

You will see a git commit editor screen twice. Just save what's there without modifications.

This code will automatically push the release to the release branch, tag it with the release 
version and update the version numbers in your POM. One commit is for updating the release branch with the 
contents of the release so we can build and push to nexus. The other commit is updating the version numbers 
for your master branch. These scripts take care of all of the versioning.
