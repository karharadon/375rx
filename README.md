Script will add second contacts and group members from IT-sphere on Linkedin.
Project is not finished and temporary suspended.
You can change some options in "config.property file.

Test will fail after sending approximately 120 invites or if user add has added a restriction(linkedin ask his email). It can be fixed if you write method wich continue test from the next page. But I didn't do that because Linkedin can block account.
So if test fail change variable "startFromThisPage" in "config.property" file on value "failed page + 1"
