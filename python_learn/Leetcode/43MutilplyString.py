class Solution:
    def multiply(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """
        if num2 == '0' or num1 == '0':
            return '0'
        n1 = [int(a) for a in list(num1)]
        n2 = [int(b) for b in list(num2)]
        nn2 = [n2[c] for c in range(len(num2)-1,-1,-1)]
        l = len(n1)
        result = []
        for i in range(l-1,-1,-1):
            re = [] #
            flag = 0
            index = 0
            for m in range(len(n2)-1,-1,-1):
                temp = n2[m] * n1[i] + flag
                flag = int(temp / 10)
                re.insert(0,temp % 10)
                index += 1
            if flag != 0:
                re.insert(0,flag)

            z = [0 for p in range(l-i-1)]
            re += z
            print()
            if len(result) < len(re):
                result = [0 for i in range(len(re)-len(result))] + result
            else:
                re = [0 for i in range(len(result) - len(re))] + re
            flag = 0
            print('re',re)
            print('result',result)
            for i in range(len(result)-1,-1,-1):
                temp = result[i] + re[i] + flag
                result[i] = temp % 10
                flag = int(temp / 10)
            if flag != 0:
                result.insert(0,flag)
            print(result)
        r1 = [str(result[q]) for q in range(len(result))]
        return "".join(r1)

s = Solution()
print(s.multiply("123","456"))
