public class CircularQueue {
    public static class Queue {
        int arr[];
        int size;
        static int rear = -1;
        static int front = -1;

        Queue(int n) {
            arr = new int[n];
            this.size = n;
        }

        public void enque(int n) {
            if((rear+1) % size == front) {
                System.out.println("Queue is full");
                return;
            }
            if(rear == -1 && front == -1) {
                front++;
            }
            rear = (rear + 1) % size;
            arr[rear] = n;
        }
        public int deque() {
            if(rear == -1 && front == -1) {
                System.out.println("Queue is empty");
                return -1;
            }
            int result = arr[front];
            if(rear == front) {
                front = -1;
                rear = -1;
            }
            else {
                front = (front + 1)% size;
            }
            return result;
        }

        public static void main(String args[]) {
            Queue q = new Queue(3);
            q.enque(1);
            q.enque(2);
            q.enque(3);
            q.deque();
            q.enque(6);
            while(!(rear == -1 && front == -1)) {
                System.out.println(q.deque());
            }
        }
    }
}
