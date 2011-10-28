package com.jetbrains.au.jslintplugin;

import java.util.List;

/**
 * User: Dmitriy Bandurin
 * Date: 26.10.11
 * Time: 18:31
 */
public class OptionType<Type> implements Comparable{
    private Integer priority;
    public static final OptionType<Boolean> BOOLEAN = new OptionType<Boolean>(3);
    public static final OptionType<Integer> NUMBER = new OptionType<Integer>(2);
    public static final OptionType<List<String>> STRING_ARRAY = new OptionType<List<String>>(1);

    public OptionType(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public int compareTo(Object o) {
        if(!(o instanceof OptionType)){
            return 0;
        }
        OptionType type = (OptionType) o;

        return this.priority.compareTo(type.getPriority());
    }
}
