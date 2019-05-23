class Solution(object):
    def reverseWords(self, s):
        """
        :type s: str
        :rtype: str
        """
        if s == "":
            return s
        s = s.strip().split()
        s = s[::-1]
        return ' '.join(s)
a = "the sky is  blue"
print(Solution().reverseWords(a))