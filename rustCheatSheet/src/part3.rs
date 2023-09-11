fn sayHello() {
    println!("Hello World.")
}
#[test]
fn main() {
    let x = 42;
    let y = -69;
    let xy = x * y;
    let z = (x, y as f32); // tuple any types inside
    let _ = [x, y];
    let _ = z.0;
    let z = [x as f32, y as f32];
    let _ = z[0];

    struct Secrets {
        x: f64,
        y: i32,
    }
    let _ = Secrets { x: -32.0, y: -42 };

    enum Fruit {
        Apple,
        Banana,
        Strawberry,
    }
    let _ = Fruit::Apple;

    let fun = sayHello;
    fun();
    println!("{} * {} = {}", x, y, xy);
    type Food = Fruit;
    let _ = Food::Apple;
    let _ = ();
}
