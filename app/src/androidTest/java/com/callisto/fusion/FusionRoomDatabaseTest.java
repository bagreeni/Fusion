import org.junit.runner.RunWith;

/**
 * Created by voxaelfox on 3/1/18.
 */
@RunWith(AndroidJUnit4.class)
public class FusionRoomDatabaseTest {
    private TaskDAO mTaskDao;
    private FusionDatabase mDb;

    @Before
    public void createDB() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, FusionDatabase.class).build();
        mUserDao = mDb.taskDAO();
    }

    @After
    public void closeDB() throws IOException {
        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        Task task = ;
        user.setName("george");
        mUserDao.insert(user);
        List<User> byName = mUserDao.findUsersByName("george");
        assertThat(byName.get(0), equalTo(user));
    }
}
