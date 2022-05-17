package com.company.reader;

import com.company.entity.Company;
import java.io.IOException;
import java.util.List;

public abstract class ReaderAbstractFactory {
    public abstract List<Company> executeFile(String fileName);
}
