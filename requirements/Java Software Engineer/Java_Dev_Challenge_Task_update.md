magnificent

A new challenger appears!

Installation

Install Python. Python2 and Python3 both work.
pip install twisted
python server.py
Okay, now you're running magnificent!
Visit http://localhost:12345 in a web browser or something.
It should throw a verbose error, or return "Magnificent!".
Now it's your turn.

Magnificent fails 25% of the time. We want to write a service to monitor the health of Magnificent.

Your user is a busy cloud system administrator. She doesn't want to run your app and look at it all the time. She wants it to run in the background.

She has tools to monitor log files, so that is one method you can use to get her attention.

Requirements

This is a daemon, or a "service", if you prefer calling it that. Once started, it should run forever.
It should check Magnificent frequently - at least several times a minute.
The service has to indicate how healthy the Magnificent service has been, over the last little while.
The service should also tell our system administrator if Magnificent has not been responding at all.
This should go without saying, but you can't use anyone else's code, unless it's under an open-source license. If you need to copy a snippet from Stack Overflow or some similar site, that's okay. But you should be the primary author of, and understand, all the code you submit.
Please refrain from including any identifying information in your submission, such as comments with your name, author information in a setup file, etc.
Platform

Note: we would prefer that your solution be operating system agnostic. If it is not please mention so, and be aware that your solution may take us slightly longer to review as we dig a Windows laptop out of the closet :)

This is what we look for.

Please solve that problem using Java.

Correctness. Does it do what we asked?
Simplicity. Does the design match the task?
Clarity. Can any competent programmer easily understand what's going on?
Generality. It shouldn't be all hardcoded, but don't make it too abstract either.
Tests and testability. It would be really great if you have tests. If not, it should be at least possible to test this.
Documentation:
Can any competent programmer get this running?
Is there just enough documentation, to tell us why the program works this way?

Upload your code as zip/tar or share the path to an public source code repository to submit your solution.