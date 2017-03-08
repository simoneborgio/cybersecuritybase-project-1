

## Vulnerabilities

Users (password and username are the same):
ted: normal user
ben: normal user

1. 2013-A8-Cross-Site Request Forgery (CSRF)

The entire web application is vulnerable to CSRF due to Spring Security misconfiguration.

The vulnerability is evident since any CSRF token is missing, but it can can be discovered using OWASP Zap too:

1. Click Manage Add-ons on main taskbar
2. Select Marketplace tab
3. Install Active and Passive scanner rules (beta) addons
4. Scan the website using Quick start or navigate to http://localhost:8080/form
5. You will see "Absence of Anti-CSRF Tokens" alert


2. 2013-A3-Cross-Site Scripting (XSS)

After signup form submission, name and address fields are persisted and then shown in done page.
Both attributes are not validated when received nor escaped when shown.


3. 2013-A1-Injection

Once again, name and address attributes in signup form are not escaped when the user submits the form.
An insecure instruction has been used to persist these attributes, so it is possible to inject arbitrary SQL code in both fields.
You can try typing any name and this address: Address 1'), ('Name 2', 'Address 2


4. 2013-A4-Insecure Direct Object References

Authenticated users can access other users saved signups using URL format http://localhost:8080/signups/{id}
where {id} is an integer counter, incremented by one after each subscription and and therefore predictable


5. 2013-A7-Missing Function Level Access Control

or 2013-A10-Unvalidated Redirects and Forwards



