struct OurIterator<'a> {
    nums: &'a [i32],
    i: usize,
    x: i32,
}

impl<'a> OurIterator<'a> {
    fn new(nums: &'a [i32], x: i32) -> OurIterator<'a> {
        OurIterator { nums, i: 0, x }
    }
}

impl Drop for OurIterator<'_> {
    fn drop(&mut self) {
        println!("Dropping OurIterator");
    }
}

impl Iterator for OurIterator<'_> {
    type Item = i32;

    fn next(&mut self) -> Option<Self::Item> {
        if self.i < self.nums.len() {
            self.x = self.nums[self.i] * self.x;
            self.i += 1;
            Some(self.x)
        } else {
            None
        }
    }
}

#[test]
fn main() {
    let nums = vec![1, 2, 3];
    {
        let mut iter = nums.iter();
        while let Some(num) = iter.next() {
            println!("{}", num);
        }
    }
}

#[test]
fn main2() {
    let mut nums = vec![1, 2, 3];
    {
        let mut nums2 = OurIterator::new(&nums, 2);
        let mut nums3 = OurIterator::new(&nums, 3);
        while let Some(num) = nums2.next() {
            println!("{}", num);
        }
        while let Some(num) = nums3.next() {
            println!("{}", num);
        }
    }
    println!("Pushing 4");
    nums.push(4);
}
