# MoshiGitHubSample
This repository is just for reproducing NoSuchMethodException with moshi-kotlin-codegen and ProGuard.

## Setup

1. Edit [token.gradle](token.gradle) and replace `YOUR GITHUB OAUTH TOKEN` with your GitHub OAuth Token.
2. Then, build with either debug or release build commands:
  - Release: `./gradlew assembleRelease`

## Reproducing NoSuchMethodException

1. Build the app **WITH RELEASE BUILD**
2. Click the fab to call API request

[token.gralde]:https://github.com/shoheikawano/MoshiGitHubSample/blob/master/app/token.gradle#L4
