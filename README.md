[![Build Status](https://travis-ci.org/wjwarren/droid-asylum.svg)](https://travis-ci.org/wjwarren/droid-asylum)
[![Coverage Status](https://coveralls.io/repos/wjwarren/droid-asylum/badge.svg?service=github)](https://coveralls.io/github/wjwarren/droid-asylum)

droid-asylum
============

A collection of useful classes for Android

## Running tests
Tests are set up based on the Android Gradle plugin [Unit testing support](http://tools.android.com/tech-docs/unit-testing-support).

To run tests, execute `gradle test`, `gradle testDebugUnitTest` or `gradle testReleaseUnitTest`.

## Coverage Reports
Run `gradle testDebugUnitTestCoverage` to generate a JaCoCo coverage report.
The HTML report can be found in the `build/reports/jacoco/testDebugUnitTestCoverage/html/` folder.
