#!/usr/bin/env bash

./gradlew assembleDebug
./gradlew assembleDebugAndroidTest

fastlane screengrab --app_package_name=com.smlnskgmail.jaman.hashchecker --tests_apk_path=app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk --app_apk_path=app/build/outputs/apk/debug/hash-checker_debug.apk --output_directory=fastlane/screenshots --test_instrumentation_runner=com.smlnskgmail.jaman.hashchecker.runner.AndroidJacocoTestRunner --use_tests_in_classes=com.smlnskgmail.jaman.hashchecker.screenshots.ScreenshotsTestSuite
