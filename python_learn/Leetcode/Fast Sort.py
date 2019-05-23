#快速排序
class FastSort:
    def fastSort(self,nums):
        self.dd(nums,0,len(nums)-1)

    def dd(self, arr, left, right):
        #控制退出
        if left >= right:
            return

        low, high = left, right
        key = arr[left]
        #移动到重合
        while(low != high):
            while low < high and arr[high] > key:
                high -= 1

            while low < high and arr[low] <= key:
                low += 1

            temp = arr[low]
            arr[low] = arr[high]
            arr[high] = temp

        #交换基准和重合
        arr[left] = arr[low]
        arr[low] = key
        #迭代下一步
        self.dd(arr, left, low - 1)
        self.dd(arr, low + 1, right)

# nums = [2,4,5,1,2,6,4,2,9,0,1]
# print(FastSort().fastSort(nums))
# print(nums)