package uy.edu.um.adt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uy.edu.um.adt.binarytree.withnode.SearchBinaryTreeImplTest;
import uy.edu.um.adt.queue.QueueImplTest;
import uy.edu.um.adt.stack.StackImplTest;

@RunWith(Suite.class)
@SuiteClasses({QueueImplTest.class, StackImplTest.class, SearchBinaryTreeImplTest.class})
public class AllTests {

}
