import math


class Solution(object):
    def primePalindrome(self, N):
        """
        :type N: int
        :rtype: int
        """
        if N == 1:
            N += 1
        if N == 2 :
            return N
        while True:

            if int("".join(list(str(N))[::-1])) == N:
                flag = True
                if N % 2 == 0:
                    continue
                for a in range(3, int(math.sqrt(N)) + 2):
                    if N % a == 0:
                        flag = False
                        break
                if flag:
                    return N
                else:
                    break
            N += 1

print(Solution().primePalindrome(6))