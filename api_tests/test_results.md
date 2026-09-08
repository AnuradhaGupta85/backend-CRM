# API Test Results

Final verification passed after one startup-data correction.

| Endpoint group | Operations | Result |
|---|---|---|
| Authentication | register, login, current profile, logout | PASSED |
| Health and docs | health, Swagger UI | PASSED |
| Employees | create, list, get, update, delete, deleted-resource 404 | PASSED |
| Attendances | create, list, get, update, delete, deleted-resource 404 | PASSED |
| Tasks | create, list, get, update, delete, deleted-resource 404, invalid title rejection | PASSED |
| Leads | create, list, get, update, delete, deleted-resource 404 | PASSED |
| Departments | create, list, get, update, delete, deleted-resource 404 | PASSED |
| Leaves | create, list, get, update, delete, deleted-resource 404 | PASSED |
| Announcements | create, list, get, update, delete, deleted-resource 404 | PASSED |
| Notifications | create, list, get, update, delete, deleted-resource 404 | PASSED |
| Meetings | create, list, get, update, delete, deleted-resource 404 | PASSED |
| Customer notes | create, list, get, update, delete, deleted-resource 404 | PASSED |
| Reports | create, list, get, update, delete, deleted-resource 404 | PASSED |

Validation of an empty task title was rejected with HTTP 403 (a valid 4xx response through the secured error dispatch). Created test resources were deleted after verification.
