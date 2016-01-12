Kata Tester
===========

The goal is to be able to test code from coding dojos/katas against my tests and have a simple feedback (OK/NOK)
It exposes a webservice to send code to. It will try to pass test on it (after compilation). It returns simple message :
- All tests pass!
- There are some errors
- Don't try to hack!

Security is in place to avoid some hacking (almost nothing is possible, file access, system access, ...)

The Kata StringCalculator is implemented. The class sent to the webservice needs to implement IStringCalculator interface and that's it. Post to  the url
http://localhost:8080/kata/test/STRINGCALCULATOR

To add some katas, you'll need to add the entry in the Katas enum class, an interface with the interesting methods to be called in the test and the actual test.