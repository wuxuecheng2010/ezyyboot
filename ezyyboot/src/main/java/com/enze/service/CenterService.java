package com.enze.service;

import java.util.List;

public interface CenterService<T> {
    List<T> getCenterOutData();
    List<T> getCenterInData();
}
