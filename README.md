Remember that math teacher in grade school who assigned a homework problem where you had to determine whether some
five-digit number is prime? The algorithm you probably used to complete that assignment is known as trial division, and
it's slow. When determining all prime numbers less than a sufficiently large integer, even a computer takes a while with
this algorithm. But a Greek mathematician saw this problem from a different angle.

This Maven project builds a WAR with a servlet that accepts a positive integer as a query parameter and efficiently
determines all prime numbers less than that integer. The WAR is deployed on Pivotal Web Services, and the servlet is
accessible at the below URL.

http://inperson.cfapps.io/soe?bound=<positive_integer>