package cn.xiaoyu.easymock;

/**
 * @author 01399268
 * @date 2020/8/24
 */
public class StudentApplication {
    IStudent student;

    public StudentApplication(IStudent student) {
        this.student = student;
    }

    public String doSomething() {
        String play = student.play();
        String run = student.run();
        String sing = student.sing();

        return play + run + sing;
    }

    public IStudent getStudent() {
        return this.student;
    }
}
