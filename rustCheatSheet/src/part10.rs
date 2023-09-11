fn double(n: i32) -> i32 {
    n * 2
}
fn triple(n: i32) -> i32 {
    n * 3
}
type MultiplierFunction = fn(i32) -> i32;
fn makeMultiplierOld(x: i32) -> MultiplierFunction {
    match x {
        2 => double,
        3 => triple,
        _ => unimplemented!("Not a valid multiplier"),
    }
}
fn makeMultiplier(x: i32) -> impl Fn(i32) -> i32 {
    move |n| n * x
}
fn makeMultiplier2(mut x: i32) -> impl FnMut(i32) -> i32 {
    move |n| {
        x += 1;
        n * x
    }
}
struct Token;
fn makeMultiplier3(mut x: i32, token: Token) -> impl FnOnce(i32) -> i32 {
    move |n| {
        drop(token);
        x += 1;
        n * x
    }
}
struct OtherMultiplierFunction {
    x: i32,
    token: Token,
}
impl OtherMultiplierFunction {
    fn callFn(&self, n: i32) -> i32 {
        n * self.x
    }
    fn callFnMut(&mut self, n: i32) -> i32 {
        self.x += 1;
        n * self.x
    }
    fn callFnOnce(self, n: i32) -> i32 {
        n * self.x
    }
}
#[test]
fn main() {
    let nums = [1, 2, 3];
    let numsAsIter = nums.into_iter();
    let multiplied = numsAsIter.map(makeMultiplierOld(2));
    for num in multiplied {
        println!("{}", num);
    }
    let numsAsIter2 = nums.into_iter();
    let multiplied = numsAsIter2.map(|n| n * 3);
    for num in multiplied {
        println!("{}", num);
    }
    let numsAsIter3 = nums.into_iter();
    let multiplied = numsAsIter3.map(makeMultiplier(3));
    for num in multiplied {
        println!("{}", num);
    }
}
