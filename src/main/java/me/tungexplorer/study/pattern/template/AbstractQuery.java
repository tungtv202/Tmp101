package me.tungexplorer.study.pattern.template;

import lombok.AllArgsConstructor;

abstract public class AbstractQuery {
    static int id = 0;

    public Result getResult() {
        // todo some thing
        return new Result(id++, generateName());
    }

    abstract protected String generateName();
}

@AllArgsConstructor
class Result {
    long id;
    String name;
}

class EmployeeQuery extends AbstractQuery {

    @Override
    protected String generateName() {
        return "employee 1";
    }
}

class ManagerQuery extends AbstractQuery {
    @Override
    protected String generateName() {
        return "manager 2";
    }
}
