# GithubUsers
An Android app for testing Github API

# Getting Started
Cause there is no OAuth login Github in app , you could generate your own ACCESS_TOKEN and put it into build gradle(Module)

```
debug {
      buildConfigField "String", "ACCESS_TOKEN", '"<your_access_token>"'
}
```

# How to get your access token
1. get to your personal github page
2. get into setting page
3. choose Developer settings
4. generate your Personal access tokens
