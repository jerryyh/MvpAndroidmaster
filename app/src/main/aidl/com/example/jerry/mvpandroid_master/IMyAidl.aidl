// IMyAidl.aidl
package com.example.jerry.mvpandroid_master;

import com.example.jerry.mvpandroid_master.bean.Person;

interface IMyAidl {
   /**
        * 除了基本数据类型，其他类型的参数都需要标上方向类型：in(输入), out(输出), inout(输入输出)
        */
       void addPerson(in Person person);

       List<Person> getPersonList();
}
