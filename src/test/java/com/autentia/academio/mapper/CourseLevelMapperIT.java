package com.autentia.academio.mapper;

import com.autentia.academio.domain.CourseLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
public class CourseLevelMapperIT {

    @Autowired  private CourseLevelMapper sut;

    @Test
    public void whenGetAllCourseLevelsShouldReturnAListWith3Elemnts(){
        List<CourseLevel> all = sut.getAll();

        assertThat(all.size(), equalTo(3));
    }

    @Test
    public void whenGetAllCourseLevelsShouldContainsLevelBasic(){
        List<CourseLevel> all = sut.getAll();

        assertTrue(all.stream().anyMatch(course -> course.getName().equals("basic")));
    }
    @Test
    public void whenGetAllCourseLevelsShouldContainsLevelMedium(){
        List<CourseLevel> all = sut.getAll();

        assertTrue(all.stream().anyMatch(course -> course.getName().equals("medium")));
    }
    @Test
    public void whenGetAllCourseLevelsShouldContainsLevelAdvanced(){
        List<CourseLevel> all = sut.getAll();

        assertTrue(all.stream().anyMatch(course -> course.getName().equals("advanced")));
    }

    @Test
    public void whenGetCourseLevelByIdShouldReturnACourseLevel(){
        CourseLevel expected = new CourseLevel(1, "basic");
        expected.setId(1);
        expected.setName("basic");

        CourseLevel result = sut.getById(1);

        assertThat(result.getId(), equalTo(expected.getId()));
    }
}
