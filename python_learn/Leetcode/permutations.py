import datetime


class Solution:
    datetime = datetime.datetime.now()
    print(datetime)
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """

        def perm(n):
            if n == 0:
                return [[nums[n]]]
            else:

                tmpres = perm(n - 1)
                z = []
                for i in tmpres:
                    for j in range(0, len(i) + 1):
                        y = i.copy()
                        y.insert(j, nums[n])
                        z.append(y)

                return z

        return perm(len(nums) - 1)