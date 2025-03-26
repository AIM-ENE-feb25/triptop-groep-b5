# Interfaces
- Autorisatie service:
  - UserAccessInfo authorizeUser(String username, String password) throws InvalidBodyException, UnauthorizedException;

## UserAccessInfo
{
  access: boolean,
  role: string
}